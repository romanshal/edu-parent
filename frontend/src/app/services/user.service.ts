import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../modules/layout/components/models/user";
import {LoginModel} from "../modules/layout/components/models/login.model";

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>("/api/user");
  }

  public saveUser(user: User): Observable<User> {
    return this.http.post<User>('/api/user/signup', user);
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
