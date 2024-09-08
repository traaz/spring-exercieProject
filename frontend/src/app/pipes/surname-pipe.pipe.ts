import { Pipe, PipeTransform } from '@angular/core';
import { Person } from '../models/person';

@Pipe({
  name: 'surnamePipe',
  standalone: true
})
export class SurnamePipePipe implements PipeTransform {
  

  transform(value: Person[], filterText: string): Person[] {
    filterText = filterText?filterText.toLocaleLowerCase():""
    return filterText?value.filter((p :Person)=> p.surname.toLocaleLowerCase().indexOf(filterText)!==-1):value;
  }

}
