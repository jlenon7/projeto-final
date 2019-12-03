// import { Curso } from './curso'
import { TipoUsuarioEnum } from './tipoUsuario';

export class Aluno {
    constructor(
       public id: number,
       public nome: string,
       public cpf: string,
       public nascimento: Date,
       public email: string,
       public senha: string,
       public celular: string,
       public disponivel: Boolean,
       public tipoUsuario: TipoUsuarioEnum
       // public funcionarios: Curso[]
      ) {  }
    
}
