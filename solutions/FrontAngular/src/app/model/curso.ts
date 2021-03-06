import { Professor } from './professor'
import { Dificuldade} from './dificuldade';
import { Time } from '@angular/common';
import { Moment } from 'moment';

export class Curso {
    constructor(
       public id: number,
       public area: string,
       public cargaHoraria: number,
       public disponivel: Boolean,
       public dificuldade: Dificuldade,
       public ministrante: Professor
      ) {  }

}
