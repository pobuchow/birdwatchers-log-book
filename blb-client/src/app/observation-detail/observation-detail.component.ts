import { Component, OnInit, Input } from '@angular/core';
import { Observation } from '../observation';

@Component({
  selector: 'app-observation-detail',
  templateUrl: './observation-detail.component.html',
  styleUrls: ['./observation-detail.component.css']
})
export class ObservationDetailComponent implements OnInit {

  @Input() observation: Observation;
  constructor() { }

  ngOnInit() {
  }

}
