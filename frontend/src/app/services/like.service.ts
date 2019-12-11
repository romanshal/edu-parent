import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post} from "../modules/layout/components/models/post";
import {Like} from "../modules/layout/components/models/like";

@Injectable()
export class LikeService {

  constructor(private httpClient: HttpClient) {
  }

  saveLike(postId:number,userId:number): Observable<Like> {
    return this.httpClient.post<Like>('/api/like/post/'+postId+"/user/"+userId, Like);
  }
}
