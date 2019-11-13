import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import{Post} from "../modules/layout/components/models/post";

@Injectable()
export class PostService {

  constructor(private httpClient: HttpClient) {
  }

  getPosts(): Observable<Post[]> {
    return this.httpClient.get<Post[]>('/api/post');
  }

  savePost(post: Post): Observable<Post> {
    return this.httpClient.post<Post>('/api/post', post);
  }

  deletePost(postId: number): Observable<void> {
    return this.httpClient.delete<void>('/api/post/' + postId)
  }
}
