import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Comment} from "../modules/layout/components/models/comment";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class CommentService {

  constructor(private httpClient: HttpClient) {
  }

  getCommentsByPostId(): Observable<Comment[]> {
    return this.httpClient.get<Comment[]>('/api/comment');
  }

}
