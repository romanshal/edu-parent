import {Comment} from "./comment";

export class Post {
  id: number;
  description: string;
  tags: string;
  userLogin:string;
  countLike: number;
  file: File;
  filename: string;
  uiComments: Comment[];
  userId:number;
}
