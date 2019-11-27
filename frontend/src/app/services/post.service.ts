import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import{Post} from "../modules/layout/components/models/post";

@Injectable()
export class PostService {

  public showAddPost: boolean;
  public showModal: boolean;

  constructor(private httpClient: HttpClient) {
  }

  // addPost(fd: FormData): Observable<Post> {
  //   return this.httpClient.post('/api/post', fd);
  // }

  getPosts(): Observable<Post[]> {
    return this.httpClient.get<Post[]>('/api/post');
  }

  savePost(fd: FormData): Observable<Post> {
    return this.httpClient.post<Post>('/api/post', fd);
  }

  deletePost(postId: number): Observable<void> {
    return this.httpClient.delete<void>('/api/post/' + postId)
  }
}
