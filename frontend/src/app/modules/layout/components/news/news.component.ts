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
  templateUrl: "./news.component.html"
})
export class NewsPageComponent implements OnInit,OnDestroy{

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
    console.log(this.currentUser);
  }

  private loadPosts(): void {
    this.subscriptions.push(this.postService.getPosts(0).subscribe(posts => {
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

  public addComment(content: NgModel,postId:number): void {
    const fd = new FormData();
    fd.append('content',this.comment.content);
    this.subscriptions.push(this.commentService.addComment(fd,postId,this.currentUser.id).subscribe())
  }

  public _deletePost(postId: number): void{
    this.subscriptions.push(this.postService.deletePost(postId).subscribe());
  }

  public _deleteComment(id: number): void{
    this.subscriptions.push(this.commentService.deleteComment(id).subscribe());
  }

  public redirectToUserPage(userId:number): void {
    this.router.navigateByUrl("/userPage?id=" + userId)
    this._closeModal();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

}
