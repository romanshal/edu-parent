import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Comment} from "../modules/layout/components/models/comment";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class CommentService {

  constructor(private httpClient: HttpClient) {
  }

  public getCommentByPostId(postId: number): Observable<Comment[]> {
    return this.httpClient.get<Comment[]>('/api/comment/post/' + postId);
  }

  addComment(comment: Comment, postId: number, userId: number): Observable<Comment> {
    return this.httpClient.post<Comment>('/api/comment/post/' + postId + "/user/" + userId, comment);
  }

  deleteComment(id: number): Observable<void> {
    return this.httpClient.delete<void>('/api/comment/' + id)
  }

}
