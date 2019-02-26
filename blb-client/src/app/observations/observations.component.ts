import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observation } from '../observation';

@Component({
  selector: 'app-observations',
  templateUrl: './observations.component.html',
  styleUrls: ['./observations.component.css']
})
export class ObservationsComponent implements OnInit {

  observation : Observation = {id:1, specificName: 'Woodpecker'};

  observations = Array<Observation>;

  constructor(private http: HttpClient) { }

  ngOnInit() {
      console.log('you are in ngOnInit');
      this.http.get<Array<Observation>>('http://localhost:8080/observation/all').subscribe(data => {
          this.observations = data; });
  }

}
