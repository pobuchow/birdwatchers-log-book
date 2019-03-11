import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule }    from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ObservationsComponent } from './observations/observations.component';
import { ObservationDetailComponent } from './observation-detail/observation-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    ObservationsComponent,
    ObservationDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
