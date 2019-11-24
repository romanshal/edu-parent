export class Post {
  id: number;
  description: string;

  static cloneBase(post: Post): Post {
    const clonedPost: Post = new Post();
    clonedPost.id = post.id;
    clonedPost.description = post.description;
    return clonedPost;
  }
}
