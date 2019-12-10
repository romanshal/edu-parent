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
import {Like} from "../models/like";
import {NgModel} from "@angular/forms";

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
  public like: Like = new Like();

  constructor(public  postService: PostService,
              private modalService: BsModalService,
              private route: ActivatedRoute,
              private router: Router,
              private storageService: StorageService,
              private likeService: LikeService) {
    this.currentUser = this.storageService.getCurrentUser();
  }

  public openDialog() {
    this.postService.showAddPost = true;
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.id = params['id'];
    });
    this.loadPostByUserId();

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

  public addLike(postId: string): void {
    const fd = new FormData();
    fd.append('post', postId);
    fd.append('userId', this.storageService.getCurrentUser().id.toString())
    this.subscriptions.push(this.likeService.saveLike(fd).subscribe())
  }

  public addComment(content: NgModel): void {
    const fd = new FormData();
    fd.append('content', this.comment.content)
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
