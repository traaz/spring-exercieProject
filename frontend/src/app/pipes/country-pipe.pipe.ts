import { Pipe, PipeTransform } from '@angular/core';
import { Person } from '../models/person';
import { Country } from '../models/country';

@Pipe({
  name: 'countryPipe',
  standalone: true
})
export class CountryPipePipe implements PipeTransform {

  transform(value: Person[], filterText: string): Person[] {
 
    return filterText?value.filter((p :Person)=> p.countryName.indexOf(filterText)!==-1):value;
  }

}
