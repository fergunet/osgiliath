#!/usr/bin/perl
use Statistics::Regression;

my $turno;
my @p1_planets; my @p1_ships; my  $p1_proc = 0;
my @p2_planets; my @p2_ships; my  $p2_proc = 0;
my @p3_planets; my @p3_ships; my  $p3_proc = 0;
my @p4_planets; my @p4_ships; my  $p4_proc = 0;
my $leer = 2;

open (LOG, '>>log_analizador.txt'); 

while ($linea=<>){
		chop($linea);
	if ($linea=~/Turn ([^ \n]*)/){
		$turno = $1;
		$leer = 2;

		$p1_proc = 0; $p2_proc = 0; $p3_proc = 0; $p4_proc = 0;
	
	#                      $1         $2            $3       $4    $5     $6     $7
	}elsif($linea=~/Player (1|2): P(1|2) - (true|false)-(\d*)\/(\d*)-(\d*)\/(\d*)/){
		if($1 == "1"){
			if($2 == "1"){
				#Hablamos del jugador 1
				if(!$p1_proc){
					push(@p1_planets,$5>0?$4/$5:0);
					push(@p1_ships, $7>0?$6/$7:0);
					$p1_proc = 1;
				}
			}elsif($2 == "2"){
				#Hablamos del jugador 2
				if(!$p2_proc){
					push(@p2_planets,$5>0?$4/$5:0);
					push(@p2_ships, $7>0?$6/$7:0);
					$p2_proc = 1;
				}

			}elsif($2 == "3"){
				#Hablamos del jugador 3
				if(!$p3_proc){
					push(@p3_planets,$5>0?$4/$5:0);
					push(@p3_ships, $7>0?$6/$7:0);
					$p3_proc = 1;
				}

			}elsif($2 == "4"){
				#Hablamos del jugador 4
				if(!$p4_proc){
					push(@p4_planets,$5>0?$4/$5:0);
					push(@p4_ships, $7>0?$6/$7:0);
					$p4_proc = 1;
				}				

			}
		}elsif($1 == "2"){
			if($2 == "1"){
				#Hablamos del jugador 2
				if(!$p2_proc){
					push(@p2_planets,$5>0?$4/$5:0);
					push(@p2_ships, $7>0?$6/$7:0);
					$p2_proc = 1;
				}
			}elsif($2 == "2"){
				#Hablamos del jugador 1
				if(!$p1_proc){
					push(@p1_planets,$5>0?$4/$5:0);
					push(@p1_ships, $7>0?$6/$7:0);
					$p1_proc = 1;
				}

			}elsif($2 == "3"){
				#Hablamos del jugador 3
				if(!$p3_proc){
					push(@p3_planets,$5>0?$4/$5:0);
					push(@p3_ships, $7>0?$6/$7:0);
					$p3_proc = 1;
				}

			}elsif($2 == "4"){
				#Hablamos del jugador 4
				if(!$p4_proc){
					push(@p4_planets,$5>0?$4/$5:0);
					push(@p4_ships, $7>0?$6/$7:0);
					$p4_proc = 1;
				}				

			}

		}elsif($1 == "3"){
			if($2 == "1"){
				#Hablamos del jugador 3
				if(!$p3_proc){
					push(@p3_planets,$5>0?$4/$5:0);
					push(@p3_ships, $7>0?$6/$7:0);
					$p3_proc = 1;
				}
			}elsif($2 == "2"){
				#Hablamos del jugador 2
				if(!$p2_proc){
					push(@p2_planets,$5>0?$4/$5:0);
					push(@p2_ships, $7>0?$6/$7:0);
					$p2_proc = 1;
				}

			}elsif($2 == "3"){
				#Hablamos del jugador 1
				if(!$p1_proc){
					push(@p1_planets,$5>0?$4/$5:0);
					push(@p1_ships, $7>0?$6/$7:0);
					$p1_proc = 1;
				}

			}elsif($2 == "4"){
				#Hablamos del jugador 4
				if(!$p4_proc){
					push(@p4_planets,$5>0?$4/$5:0);
					push(@p4_ships, $7>0?$6/$7:0);
					$p4_proc = 1;
				}				

			}

		}elsif($1 == "4"){
			if($2 == "1"){
				#Hablamos del jugador 4
				if(!$p4_proc){
					push(@p4_planets,$5>0?$4/$5:0);
					push(@p4_ships, $7>0?$6/$7:0);
					$p4_proc = 1;
				}
			}elsif($2 == "2"){
				#Hablamos del jugador 2
				if(!$p2_proc){
					push(@p2_planets,$5>0?$4/$5:0);
					push(@p2_ships, $7>0?$6/$7:0);
					$p2_proc = 1;
				}

			}elsif($2 == "3"){
				#Hablamos del jugador 3
				if(!$p3_proc){
					push(@p3_planets,$5>0?$4/$5:0);
					push(@p3_ships, $7>0?$6/$7:0);
					$p3_proc = 1;
				}

			}elsif($2 == "4"){
				#Hablamos del jugador 1
				if(!$p1_proc){
					push(@p1_planets,$5>0?$4/$5:0);
					push(@p1_ships, $7>0?$6/$7:0);
					$p1_proc = 1;
				}				
			}
		}
	}
}

#foreach(@p1_ships) { print "$_\r\n"; }

my $p1_planets_area = 0.0; my $p1_ships_area = 0.0;
my $p2_planets_area = 0.0; my $p2_ships_area = 0.0;
my $p3_planets_area = 0.0; my $p3_ships_area = 0.0;
my $p4_planets_area = 0.0; my $p4_ships_area = 0.0;

my $reg1 = Statistics::Regression->new("Title", ["Turno", "Slope"]);
my $reg2 = Statistics::Regression->new("Title", ["Turno", "Slope"]);
my $reg3 = Statistics::Regression->new("Title", ["Turno", "Slope"]);
my $reg4 = Statistics::Regression->new("Title", ["Turno", "Slope"]);

print LOG "Turnos;P1;P2;P3;P4";
for($i = 1; $i< $turno; $i++ ){
	print LOG $i."Naves;". $p1_ships[$i] .";". $p2_ships[$i] .";". $p3_ships[$i] .";". $p4_ships[$i] ."\r\n";
	#print LOG $i.";". $p1_planets[$i] .";". $p2_planets[$i] .";". $p3_planets[$i] .";". $p4_planets[$i] ."\r\n";

	$reg1->include( $p1_ships[$i] , [ 1.0 , $i]);
	#$reg2->include( $p2_ships[$i] , [ 1.0 , $i]);
	#$reg3->include( $p3_ships[$i] , [ 1.0 , $i]);
	#$reg4->include( $p4_ships[$i] , [ 1.0 , $i]);

	# $reg1->include( $p1_planets[$i] , [ 1.0 , $i]);
	# $reg2->include( $p2_planets[$i] , [ 1.0 , $i]);
	# $reg3->include( $p3_planets[$i] , [ 1.0 , $i]);
	# $reg4->include( $p4_planets[$i] , [ 1.0 , $i]);
}

 my @p1_theta = $reg1->theta();
 #my @p2_theta = $reg2->theta();
 #my @p3_theta = $reg3->theta();
 #my @p4_theta = $reg4->theta();

#print " P1\n";
print @p1_theta[0]."\n";
print @p1_theta[1];
print LOG "P1\t".@p1_theta[1]. "x + ". @p1_theta[0] . "\tR2 ". $reg1->rsq() ;
print LOG "\n";
print "\n";
/*
print " P2\n";
print @p2_theta[0]."\n";
print @p2_theta[1];
print LOG "P2\t".@p2_theta[1]. "x + ". @p2_theta[0] . "\tR2 ". $reg2->rsq() ;
print LOG "\n";
print "\n";

print " P3\n";
print @p3_theta[0]."\n";
print @p3_theta[1];
print LOG "P3\t".@p3_theta[1]. "x + ". @p3_theta[0] . "\tR2 ". $reg3->rsq() ;
print LOG "\n";
print "\n";

print " P4\n";
print @p4_theta[0]."\n";
print @p4_theta[1];
print LOG "P4\t".@p4_theta[1]. "x + ". @p4_theta[0] . "\tR2 ". $reg4->rsq() ;
print LOG "\n";
print "\n";*/


