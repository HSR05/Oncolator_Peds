import { Component, OnInit } from '@angular/core';
import { DataService } from './../data.service';
import { SharedService } from '../shared.service';

interface DOSAGE {
  id: Number;
  name: String;
  dosage: Number[];
}

@Component({
  selector: 'app-result-table',
  templateUrl: './result-table.component.html',
  styleUrls: ['./result-table.component.css']
})
export class ResultTableComponent implements OnInit {

  dosageData: any;
  constructor(public dataservice: DataService, private sharedService: SharedService) { }
  ngOnInit(): void {
    this.sharedService.items$.subscribe(items => {
      this.dosageData = items;
      console.log('Updated dosage data: ', this.dosageData);
      // this.initComponent();
    });
    this.dosageData = this.dataservice.dosageData;
    console.log('Loaded dosage data: ', this.dosageData);
  }
  initComponent(): void {
    console.log('initComponent called');
    this.ngOnInit();
  }
}

