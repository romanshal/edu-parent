export class BillingAccount {
  id: string;
  address: string;
  username: string;
  email: string;


  constructor(id: string, address: string, username: string, email: string) {
    this.id = id;
    this.address = address;
    this.username = username;
    this.email = email;
  }
}


