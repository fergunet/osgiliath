#!/usr/bin/perl

use strict;
use warnings;

use Image::Magick;

my $dir_name = shift || ".";

my @images = glob "$dir_name/*.png $dir_name/*.jpg";

for my $i ( @images ) {
  my $image = new Image::Magick;
  $image->Read($i);
  my $bb = $image->Get('bounding-box');
  my ($sizex, $sizey, $startx, $starty ) = ($bb =~ /(\d+)x(\d+)\+(\d+)\+(\d+)/ );
  my $new_startx = $startx + rand( $sizex);
  my $new_starty = $starty + rand( $sizey);
  my $new_sizex = rand( $startx + $sizex - $new_startx );
  my $new_sizey = rand( $starty + $sizey - $new_starty );
  $image->Crop( geometry => $new_sizex."x$new_sizey+$new_startx+$new_starty");
  $i=~ s/$dir_name\//$dir_name\/p-/;
  $image->Write($i);
}

