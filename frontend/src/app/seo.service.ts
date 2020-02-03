import { Injectable } from '@angular/core';
import { Meta } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SeoService {

  constructor(private meta: Meta, private actRoute: ActivatedRoute) {
    meta.addTags([
      { name: 'description', content: 'My Job Board: mon concentrateur d\'offres d\'emploi.' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { name: 'robots', content: 'INDEX, FOLLOW' },
      //{ name: 'author', content: '' },
      { name: 'date', content: '2020-02-03', scheme: 'YYYY-MM-DD' },
      { httpEquiv: 'Content-Type', content: 'text/html' },
      { property: 'og:title', content: "My Job Board" },
      //{ property: 'og:type', content: "website" },
      { charset: 'UTF-8' }
    ]);

    var q = "informatique", l = "lyon";
    this.actRoute.queryParams.subscribe(params => {
      if (params['q']) q = params['q'];
      if (params['l']) l = params['l'];
    });
    meta.addTag({ property: 'keywords', content: "travail,offre,emploi,recherche,opportunité," + q + "," + l });
  }

}
