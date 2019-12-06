export class User{
  id: number;
  login: string;
  password:string;
  name: string;


  constructor(login: string, password: string, name: string) {
    this.login = login;
    this.password = password;
    this.name = name;
  }
}
