import { NgModule } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatSliderModule }  from '@angular/material/slider';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule} from '@angular/material/icon';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';




const MaterialComponents=[MatInputModule,
                          MatSliderModule,
                          MatToolbarModule,
                          MatGridListModule,
                          MatFormFieldModule,
                          MatRadioModule,
                          MatSelectModule,
                          MatDatepickerModule,
                          MatCheckboxModule,
                          MatButtonModule,
                          MatNativeDateModule,
                          MatDialogModule,
                          MatIconModule,
                          MatSnackBarModule,
                          MatCardModule,
                          MatListModule,
                          MatSidenavModule];

@NgModule({
  
  imports: [MaterialComponents],
  exports: [MaterialComponents]
})
export class MaterialModule { }
