import {Component, OnDestroy, OnInit} from "@angular/core";
import {Post} from "../../../layout/components/models/post";
import {PostService} from "../../../../services/post.service";
import {FormGroup, NgModel} from "@angular/forms";
import {Subscription} from "rxjs";
import {StorageService} from "../../../../services/storage.service";

@Component({
  selector: "app-add-post",
  templateUrl: "./add-post.component.html"
})
export class AddPostComponent implements OnDestroy {

  public post: Post = new Post();
  private subscriptions: Subscription[] = [];

  constructor(private  postService: PostService,
              private storageService: StorageService) {
  }

  close() {
    this.postService.showAddPost = false;
  }

  public addPost(description: NgModel): void {
    const fd = new FormData();
    // fd.append('file',this.post.file);
    fd.append('description', this.post.description);
    fd.append('login', this.storageService.getCurrentUser().login);
    this.subscriptions.push(this.postService.savePost(fd).subscribe(() => {
      this.close()
      this.refreshPost()
    }));
  }

  private refreshPost(): void {
    this.post = new Post();
  }

  public onFileSelected(event) {
    this.post.file = <File>event.target.files[0];
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
