import { Component, OnInit } from '@angular/core';

import { Observation } from './observation';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit{

        title = "Birdwatchers Log Book";

        constructor(){ 	}
}
