import {Role} from "./role";

export class User {
  id: number;
  login: string;
  role: Role;
  friends: User[];
  friendOf: User[];


}
