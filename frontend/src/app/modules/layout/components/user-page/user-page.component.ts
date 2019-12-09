import {Component, OnDestroy, OnInit, TemplateRef} from "@angular/core";
import {PostService} from "../../../../services/post.service";
import {Post} from "../models/post";
import {Subscription} from "rxjs";
import {User} from "../models/user";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Comment} from "../models/comment";
import {ActivatedRoute, Router} from "@angular/router";
import {StorageService} from "../../../../services/storage.service";

@Component({
  selector: "user-page",
  templateUrl: "./user-page.component.html"
})
export class UserPageComponent implements OnInit,OnDestroy {

  public comment: Comment;
  public currentUser: User = new User();
  private subscriptions: Subscription[] = [];
  public posts: Post[];
  public post: Post;
  public user: User;
  public modalRef: BsModalRef;
  public id: number;
  public name:string;

  constructor(public  postService: PostService,
              private modalService: BsModalService,
              private route: ActivatedRoute,
              private router: Router,
              private storageService: StorageService) {
    this.currentUser = this.storageService.getCurrentUser();
  }

  public openDialog() {
    this.postService.showAddPost = true;
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.id = params['id'];
    });
    this.loadCardByUserId();

  }

  private loadCardByUserId(): void {
    this.subscriptions.push(this.postService.getPostByUserId(this.id).subscribe(posts => {
      this.posts = posts as Post[];
      console.log(posts);
      this.posts.forEach(post => {this.post = post});
      this.name=this.currentUser.login;
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

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
