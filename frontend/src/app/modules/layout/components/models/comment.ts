import {User} from "./user";
import {Post} from "./post";

export class Comment {
  id:number;
  content: string;
  uiUser: User;
  timeCreation: Date;
}
