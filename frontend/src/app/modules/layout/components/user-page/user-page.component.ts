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
import {NgModel} from "@angular/forms";
import {CommentService} from "../../../../services/comment.service";
import {AuthService} from "../../../../services/auth-service";

@Component({
  selector: "user-page",
  templateUrl: "./user-page.component.html"
})
export class UserPageComponent implements OnInit, OnDestroy {

  public comment: Comment = new Comment();
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
  content: string = '';

  constructor(public  postService: PostService,
              private modalService: BsModalService,
              private route: ActivatedRoute,
              private router: Router,
              private storageService: StorageService,
              private likeService: LikeService,
              private commentService:CommentService,
              private authService: AuthService,) {
    this.currentUser = this.storageService.getCurrentUser();
    this.isAdmin = this.authService.isAdmin();
  }

  public openDialog() {
    this.postService.showAddPost = true;
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.id = params['id'];
    });
    this.loadPostByUserId();
console.log(this.currentUser);
  }

  private loadPostByUserId(): void {
    this.subscriptions.push(this.postService.getPostByUserId(0, this.id).subscribe(posts => {
      this.posts = posts as Post[];
      console.log(posts);
      this.posts.forEach(post => {
        this.post = post
      });
      this.name = this.post.userLogin;
    }))
  }

  public _openPostModal(template: TemplateRef<any>): void {
    this.modalRef = this.modalService.show(template);
  }

  public _closeModal(): void {
    this.modalRef.hide();
  }

  public redirectToNews(): void {
    this.router.navigateByUrl("/news");
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
    this.subscriptions.push(this.likeService.saveLike(postId, this.currentUser.id).subscribe());
  }

  public addComment(content: string,postId:number): void {
    this.comment.content=content;
    // this.comment.timeCreation=new Date();
    this.subscriptions.push(this.commentService.addComment(this.comment,postId,this.currentUser.id).subscribe());
    // const fd = new FormData();
    // fd.append('content',this.comment.content);
    // this.subscriptions.push(this.commentService.addComment(fd,postId,this.currentUser.id).subscribe());
  }

  public _deletePost(postId: number): void{
    this.subscriptions.push(this.postService.deletePost(postId).subscribe());
  }

  public _deleteComment(id: number): void{
    this.subscriptions.push(this.commentService.deleteComment(id).subscribe());
  }

  public _updatePost(): void {
    this.loadPostByUserId();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
