import { Pipe, PipeTransform } from '@angular/core';
import { Person } from '../models/person';

@Pipe({
  name: 'statePipe',
  standalone: true
})
export class StatePipePipe implements PipeTransform {

  transform(value: Person[], filterText: string): Person[] {
 
    return filterText?value.filter((p :Person)=> p.stateName.indexOf(filterText)!==-1):value;
  }

}
