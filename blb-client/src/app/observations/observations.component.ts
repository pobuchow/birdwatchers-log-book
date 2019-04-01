import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observation } from '../observation';

@Component({
  selector: 'app-observations',
  templateUrl: './observations.component.html',
  styleUrls: ['./observations.component.css']
})
export class ObservationsComponent implements OnInit {

  observations : Array<Observation>;

  request: string;

  constructor(private http: HttpClient) { }

  selectedObservation: Observation;

  onSelect(observation: Observation): void {
    console.log("You are in onSelect");
    this.selectedObservation = observation;
    console.log(observation);
  }

  ngOnInit() {
      console.log('you are in ngOnInit');
      this.http.get<Array<Observation>>('http://localhost:8080/observation/all').subscribe(data => {
          console.log(data);
          this.observations = data; }
        );
  }

  deleteObservation(observation: Observation){
    this.request = 'http://localhost:8080/observation/delete?id=' + observation.id;
    console.log(this.request);
    return this.http.get(this.request).subscribe(data => {
      console.log(data);
    });
  }

  editObservation(observation_id : String, species : string, location : string, date : string){
    this.request = 'http://localhost:8080/observation/edit?observation_id=' + observation_id + '&species=' + species + '&location=' + location + '&date=' + date;
    console.log(this.request);
    return this.http.get(this.request).subscribe(data => {
      console.log(data);
    });
  }

  addObservation(user_id: string, species: string, location: string, date: string){
    this.request = 'http://localhost:8080/observation/add?user_id=' + user_id + '&species=' + species + '&location=' + location + '&date=' + date;
    console.log(this.request);
    return this.http.get(this.request).subscribe(data => {
      console.log(data);
    });
  }
}
