export class TicketType {
    id : number ;
    ticketType: string;
    description: string;
    workFlow : string ;
    archiveId :string;



  constructor (id :number, ticketType: string,description: string,workFlow : string ,archiveId :string)
  {

    this.id =id;
    this.ticketType=ticketType;
    this.description=description;
    this.workFlow=workFlow;
    this.archiveId=archiveId;
  }
}