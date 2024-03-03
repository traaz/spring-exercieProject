import { Pipe, PipeTransform } from '@angular/core';
import { Person } from '../models/person';

@Pipe({
  name: 'filterPipe',
  standalone: true
})
export class FilterPipePipe implements PipeTransform {

  transform(value: Person[], filterText: string): Person[] {
    filterText = filterText?filterText.toLocaleLowerCase():""
    return filterText?value.filter((p :Person)=> p.name.toLocaleLowerCase().indexOf(filterText)!==-1):value;
  }

}
