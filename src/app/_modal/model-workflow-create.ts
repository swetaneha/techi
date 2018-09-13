export class WorkflowCreate{

    name:String;
    description:String;
    status:number[];
    ranks:number[];
    //isActive:String;
    constructor (name:String, description:String)
//, isActive:String
//,status:String,ranks:String
{


this.name=name;

this.description=description;

// this.status=status;
// this.ranks=ranks;
//this.isActive=isActive;
}


}