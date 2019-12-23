import {Component, OnDestroy, OnInit, TemplateRef} from "@angular/core";
import {PostService} from "../../../../services/post.service";
import {Post} from "../models/post";
import {Subscription} from "rxjs";
import {User} from "../models/user";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Comment} from "../models/comment";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../../../services/storage.service";
import {LikeService} from "../../../../services/like.service";
import {CommentService} from "../../../../services/comment.service";
import {AuthService} from "../../../../services/auth-service";
import {UserService} from "../../../../services/user.service";
import {NgModel} from "@angular/forms";

@Component({
  selector: "user-page",
  templateUrl: "./user-page.component.html",
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit, OnDestroy {

  public comment: Comment = new Comment();
  public comments: Comment[] = [];
  public currentUser: User = new User();
  private subscriptions: Subscription[] = [];
  public posts: Post[];
  public post: Post = new Post();
  public user: User;
  public modalRef: BsModalRef;
  public id: number;
  public name: string;
  public page: number = 1;
  public isAdmin: boolean;
  content: string = '';
  public userWhosePage: User = new User();
  nameBtn: string = 'subscribe';

  constructor(public  postService: PostService,
              private modalService: BsModalService,
              private route: ActivatedRoute,
              private router: Router,
              private storageService: StorageService,
              private likeService: LikeService,
              private commentService: CommentService,
              private authService: AuthService,
              private userService: UserService) {
    this.currentUser = this.storageService.getCurrentUser();
    this.isAdmin = this.authService.isAdmin();
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.id = params['id'];
    });
    this.loadPostByUserId();
    this.loadUserById();
  }

  public addPost(description: NgModel): void {
    const fd = new FormData();
    fd.append('file', this.post.file);
    fd.append('description', this.post.description);
    fd.append('login', this.storageService.getCurrentUser().login);
    this.subscriptions.push(this.postService.savePost(fd).subscribe(() => {
      this.refreshPost();
      this._closeModal();
      this.loadPostByUserId();
    }));
  }

  private refreshPost(): void {
    this.post = new Post();
  }

  public onFileSelected(event) {
    this.post.file = <File>event.target.files[0];
  }

  private loadPostByUserId(): void {
    this.subscriptions.push(this.postService.getPostByUserId(0, this.id).subscribe(posts => {
      this.posts = posts as Post[];
      this.posts.forEach(post => {
        this.post = post;
      });
    }));
    this.loadUserById();
  }

  private loadUserById(): void {
    this.subscriptions.push(this.userService.getUserById(this.id).subscribe(user => {
      this.userWhosePage = user;
    }));
  }

  public _openModal(template: TemplateRef<any>, postId: number): void {
    this.modalRef = this.modalService.show(template);
    this.loadComments(postId);
  }

  public _openSubscriptionModal(template: TemplateRef<any>): void {
    this.modalRef = this.modalService.show(template);
  }

  public _closeModal(): void {
    this.modalRef.hide();
  }

  public redirectToNews(): void {
    this.router.navigateByUrl("/news");
  }

  public redirectToFriendPage(userId:number): void {
    this.router.navigateByUrl("/userPage?id=" + userId)
    this._closeModal();
  }

  public nextPage(): void {
    this.subscriptions.push(this.postService.getPostByUserId(this.page, this.id).subscribe((posts: Post[]) => {
      for (let post of posts) {
        this.posts.push(post);
      }
    }));
    this.page = this.page + 1;
  }

  public addLike(postId: number): void {
    this.subscriptions.push(this.likeService.saveLike(postId, this.currentUser.id).subscribe(() => {
      this.loadPostByUserId();
      this._closeModal();
    }));
  }

  private loadComments(postId: number): void {
    this.subscriptions.push(this.commentService.getCommentByPostId(postId).subscribe(comments => {
      this.comments = comments as Comment[];
    }));
  }

  public addComment(content: string, postId: number): void {
    this.comment.content = content;
    this.comment.timeCreation = new Date();
    this.subscriptions.push(this.commentService.addComment(this.comment, postId, this.currentUser.id).subscribe(() => {
      this.refreshComment();
      this.loadComments(postId);
    }));
  }

  public _deletePost(postId: number): void {
    this.subscriptions.push(this.postService.deletePost(postId).subscribe(() => {
      this.loadPostByUserId();
      this._closeModal();
    }));

  }

  public _deleteComment(id: number, postId: number): void {
    this.subscriptions.push(this.commentService.deleteComment(id).subscribe(() => {
      this.loadComments(postId)
    }));
  }

  private subscription(userId: number): void {
    this.subscriptions.push(this.userService.subscription(userId, this.currentUser).subscribe((user) => {
        this.currentUser = user;
      }
    ));
  }

  private refreshComment(): void {
    this.content = '';
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
