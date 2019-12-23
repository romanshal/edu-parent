import {Component, OnDestroy, OnInit, TemplateRef} from "@angular/core";
import {Subscription} from "rxjs";
import {Post} from "../models/post";
import {PostService} from "../../../../services/post.service";
import {Comment} from "../models/comment";
import {User} from "../models/user";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../../../services/storage.service";
import {LikeService} from "../../../../services/like.service";
import {CommentService} from "../../../../services/comment.service";
import {AuthService} from "../../../../services/auth-service";
import {NgModel} from "@angular/forms";

@Component({
  selector: "news-page",
  templateUrl: "./news.component.html",
  styleUrls: ['./news.component.css']
})
export class NewsPageComponent implements OnInit,OnDestroy{

  public comment: Comment = new Comment();
  public comments: Comment[] = [];
  content: string = '';
  public currentUser: User = new User();
  private subscriptions: Subscription[] = [];
  public posts: Post[];
  public post: Post;
  public user: User;
  public modalRef: BsModalRef;
  public id: number;
  public name: string;
  public page: number = 1;
  public isAdmin: boolean;

  constructor(public  postService: PostService,
              private modalService: BsModalService,
              private route: ActivatedRoute,
              private router: Router,
              private storageService: StorageService,
              private likeService: LikeService,
              private commentService:CommentService,
              private authService: AuthService,) {
    this.currentUser = this.storageService.getCurrentUser();
    this.isAdmin = !this.authService.isAdmin();
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.id = params['id'];
    });
    this.loadPosts();
  }

  private loadPosts(): void {
    this.subscriptions.push(this.postService.getPosts(0).subscribe(posts => {
      this.posts = posts as Post[];
      this.posts.forEach(post => {
        this.post = post
      });
      this.name = this.post.userLogin;
    }))
  }

  public _openModal(template: TemplateRef<any>, postId: number): void {
    this.modalRef = this.modalService.show(template);
    this.loadComments(postId);
  }

  public _closeModal(): void {
    this.modalRef.hide();
  }

  public addLike(postId: number): void {
    this.subscriptions.push(this.likeService.saveLike(postId, this.currentUser.id).subscribe(() => {
      this._closeModal();
    }));
  }

  public addNewComment(content: string, postId: number): void {
    this.comment.content = content;
    this.comment.timeCreation = new Date();
    this.subscriptions.push(this.commentService.addComment(this.comment, postId, this.currentUser.id).subscribe(() => {
      this.refreshComment();
      this.loadComments(postId);
    }));
  }

  private loadComments(postId: number): void {
    this.subscriptions.push(this.commentService.getCommentByPostId(postId).subscribe(comments => {
      this.comments = comments as Comment[];
    }));
  }

  public _deleteComment(id: number, postId: number): void {
    this.subscriptions.push(this.commentService.deleteComment(id).subscribe(() => {
      this.loadComments(postId)
    }));
  }

  private refreshComment(): void {
    this.comment = new Comment();
  }

  public redirectToFriendPage(userId:number): void {
    this.router.navigateByUrl("/userPage?id=" + userId)
    this._closeModal();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

}
