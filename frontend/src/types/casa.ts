export type Convidados = {
    id: number;
    nome: string;
    acompanhante:0;
    presenta:boolean;
    email:string;
    telefone:string;
    senha:string;
    presentes: Presentes[];
    fotos:Fotos[];
}

export type Fotos = {
    id:number;
    imageURL:string;
}

export type Presentes ={
    id:number;
    nome:string;
    categoria:number;
    escolhido:boolean;
}

export type ConvidadosPage ={
    content?: Convidados[];
    last:boolean;
    totalPages:number;
    totalElements:number;
    size?:number;
    number:number;
    first:boolean;
    numberOfElements?:number;
    empty?:boolean;
}