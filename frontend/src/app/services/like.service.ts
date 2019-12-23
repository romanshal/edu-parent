import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Like} from "../modules/layout/components/models/like";
import {Comment} from "../modules/layout/components/models/comment";

@Injectable()
export class LikeService {

  constructor(private httpClient: HttpClient) {
  }

  saveLike(postId:number,userId:number): Observable<Like> {
    return this.httpClient.post<Like>('/api/like/post/'+postId+"/user/"+userId, Like);
  }

  public getLikesByPostId(postId: number): Observable<Like[]> {
    return this.httpClient.get<Like[]>('/api/like/post/' + postId);
  }
}
