import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Apiservice } from '../../apiservice';
import { CommonModule } from '@angular/common';
import Vary from '../../utils/Vary';
import { Tagservice } from '../tagservice';
import { TagModel } from '../../models/tagmodel';

@Component({
  selector: 'app-tagall',
  imports: [CommonModule, RouterLink],
  templateUrl: './tagall.html',
  styleUrl: './tagall.css',
})
export class Tagall {
  tags: TagModel[] = [];
  constructor(private apiService: Apiservice, private tagService: Tagservice) {}

  ngOnInit() {
    this._loadTags();
  }

  _loadTags() {
    this.apiService.getTags().subscribe((cts) => {
      // Map all categories at once with proper image URLs
      this.tags = cts.map((tag: TagModel) => ({
        ...tag,
        image: Vary.getImage(tag.image),
      }));
      console.log('Tags after loading:', this.tags);
    });
  }

  getImageUrl(imagePath: string): string {
    const url = Vary.getImage(imagePath);
    console.log('Image URL:', url);
    return url;
  }

  deleteTag(id: number) {
    this.tagService.delete(id).subscribe(() => {
      this._loadTags();
    });
  }
}
