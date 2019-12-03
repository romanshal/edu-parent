import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../modules/layout/components/models/user";
import {LoginModel} from "../modules/layout/components/models/login.model";

@Injectable()
export class UserService {

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>("/api/user");
  }

  constructor(private http: HttpClient) {
  }

  public generateToken(login: LoginModel): Observable<AuthToken> {
    return this.http.post<AuthToken>("/api/token/generate-token", login);
  }

  public getAuthorizedUser(): Observable<User> {
    return this.http.get<User>("/api/user/current");
  }

}

export interface AuthToken {
  readonly token: string;
}
