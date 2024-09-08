import { Pipe, PipeTransform } from '@angular/core';
import { Person } from '../models/person';

@Pipe({
  name: 'agePipe',
  standalone: true
})
export class AgePipePipe implements PipeTransform {

  transform(value: Person[], filterText: number): Person[] {
    return filterText?value.filter((p :Person)=> p.age==filterText):value;
  }

}
