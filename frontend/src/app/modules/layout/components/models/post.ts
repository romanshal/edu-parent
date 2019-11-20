export class Post {
  id: number;
  tags: string;
  description: string;

  static cloneBase(post: Post): Post {
    const clonedPost: Post = new Post();
    clonedPost.id = post.id;
    clonedPost.tags = post.tags;
    clonedPost.description = post.description;
    return clonedPost;
  }
}
