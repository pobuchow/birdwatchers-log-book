import { Component, OnInit } from '@angular/core';
import { HttpClientModule }    from '@angular/common/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-root',
  template: `
    <div style="text-align:center">
      <h1>
        {{greeting}}
      </h1>
      </div>
    <router-outlet></router-outlet>
  `,
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit{

        greeting : string;

        constructor(private http: HttpClient){ 	}

        ngOnInit() {

            console.log('you are in ngOnInit');
            this.http.get<string>('http://localhost:8080/user/greet', {responseType: 'text'}).subscribe(data => {
                this.greeting = data; });
  	}
}

