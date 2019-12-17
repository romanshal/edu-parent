import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Comment} from "../modules/layout/components/models/comment";
import {HttpClient} from "@angular/common/http";
import {Like} from "../modules/layout/components/models/like";

@Injectable()
export class CommentService {

  constructor(private httpClient: HttpClient) {
  }

  addComment(comment: Comment, postId: number, userId: number): Observable<Comment> {
    return this.httpClient.post<Comment>('/api/comment/post/' + postId + "/user/" + userId, comment);
  }

  deleteComment(id: number): Observable<void> {
    return this.httpClient.delete<void>('/api/comment/' + id)
  }

}
