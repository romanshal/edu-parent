import {Comment} from "./comment";

export class Post {
  id: number;
  description: string;
  tags: string;
  userLogin:string;
  countLike: number;
  file: File;
  filePath: string;
  uiComments: Comment[];
}
