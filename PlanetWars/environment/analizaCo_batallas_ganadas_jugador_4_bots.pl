#!/usr/bin/perl



$turno = 0;
my $p1_turno = 0; my $p1_puesto = 0; my  $p1_proc = 0;
my $p2_turno = 0; my $p2_puesto = 0; my  $p2_proc = 0;
my $p3_turno = 0; my $p3_puesto = 0; my  $p3_proc = 0;
my $p4_turno = 0; my $p4_puesto = 0; my  $p4_proc = 0;
my $puesto = 4;

open (LOG, '>>log_analizador.txt'); 

while ($linea=<>){
		chop($linea);
	if ($linea=~/Turn ([^ \n]*)/){
		$turno = $1;
		$leer = 4;

		$p1_proc = 0; $p2_proc = 0; $p3_proc = 0; $p4_proc = 0;

		#                       $1
	}elsif($linea=~/Player (1|2|3|4) Wins!/){
		if($1 eq "1"){ $p1_turno = $turno ; $p1_puesto = 1; }
		if($1 eq "2"){ $p2_turno = $turno ; $p2_puesto = 1; }
		if($1 eq "3"){ $p3_turno = $turno ; $p3_puesto = 1; }
		if($1 eq "4"){ $p4_turno = $turno ; $p4_puesto = 1; }
	#                           $1         $2            $3       $4    $5     $6     $7
	}elsif($linea=~/Player (1|2|3|4): P(1|2|3|4) - (true|false)-(\d*)\/(\d*)-(\d*)\/(\d*)/){
		if($1 == "1"){
			if($2 == "1"){
				#Hablamos del jugador 1
				if(!$p1_proc){
					if($3 eq "false"){
						if($turno > 1 && $p1_turno == 0 && $3){
							$p1_turno = $turno;
							$p1_puesto = $puesto;
							$puesto--;
						}
					}
					$p1_proc = 1;

				}
			}elsif($2 == "2"){
				#Hablamos del jugador 2
				if(!$p2_proc){
					if($3 eq "false"){
						if($turno > 1 && $p2_turno == 0 && $3){
							$p2_turno = $turno;
							$p2_puesto = $puesto;
							$puesto--;
						}
					}

					$p2_proc = 1;
				}

			}elsif($2 == "3"){
				#Hablamos del jugador 3
				if(!$p3_proc){
					if($3 eq "false"){
						if($turno > 1 && $p3_turno == 0 && $3){
							$p3_turno = $turno;
							$p3_puesto = $puesto;
							$puesto--;
						}
					}

					$p3_proc = 1;
				}

			}elsif($2 == "4"){
				#Hablamos del jugador 4
				if(!$p4_proc){
					if($3 eq "false"){
						if($turno > 1 && $p4_turno == 0 && $3){
							$p4_turno = $turno;
							$p4_puesto = $puesto;
							$puesto--;
						}
					}

					$p4_proc = 1;
				}				

			}
		}elsif($1 == "2"){
			if($2 == "1"){
				#Hablamos del jugador 2
				if(!$p2_proc){
					if($3 eq "false"){
						if($turno > 1 && $p2_turno == 0 && $3){
							$p2_turno = $turno;
							$p2_puesto = $puesto;
							$puesto--;
						}
					}

					$p2_proc = 1;
				}
			}elsif($2 == "2"){
				#Hablamos del jugador 1
				if(!$p1_proc){
					if($3 eq "false"){
						if($turno > 1 && $p1_turno == 0 && $3){
							$p1_turno = $turno;
							$p1_puesto = $puesto;
							$puesto--;
						}
					}

					$p1_proc = 1;
				}

			}elsif($2 == "3"){
				#Hablamos del jugador 3
				if(!$p3_proc){
					if($3 eq "false"){
						if($turno > 1 && $p3_turno == 0 && $3){
							$p3_turno = $turno;
							$p3_puesto = $puesto;
							$puesto--;
						}
					}

					$p3_proc = 1;
				}

			}elsif($2 == "4"){
				#Hablamos del jugador 4
				if(!$p4_proc){
					if($3 eq "false"){
						if($turno > 1 && $p4_turno == 0 && $3){
							$p4_turno = $turno;
							$p4_puesto = $puesto;
							$puesto--;
						}
					}

					$p4_proc = 1;
				}				

			}

		}elsif($1 == "3"){
			if($2 == "1"){
				#Hablamos del jugador 3
				if(!$p3_proc){
					if($3 eq "false"){
						if($turno > 1 && $p3_turno == 0 && $3){
							$p3_turno = $turno;
							$p3_puesto = $puesto;
							$puesto--;
						}
					}

					$p3_proc = 1;
				}
			}elsif($2 == "2"){
				#Hablamos del jugador 2
				if(!$p2_proc){
					if($3 eq "false"){
						if($turno > 1 && $p2_turno == 0 && $3){
							$p2_turno = $turno;
							$p2_puesto = $puesto;
							$puesto--;
						}
					}

					$p2_proc = 1;
				}

			}elsif($2 == "3"){
				#Hablamos del jugador 1
				if(!$p1_proc){
					if($3 eq "false"){
						if($turno > 1 && $p1_turno == 0 && $3){
							$p1_turno = $turno;
							$p1_puesto = $puesto;
							$puesto--;
						}
					}

					$p1_proc = 1; 
				}

			}elsif($2 == "4"){
				#Hablamos del jugador 4
				if(!$p4_proc){
					if($3 eq "false"){
						if($turno > 1 && $p4_turno == 0 && $3){
							$p4_turno = $turno;
							$p4_puesto = $puesto;
							$puesto--;
						}
					}

					$p4_proc = 1;
				}				

			}

		}elsif($1 == "4"){
			if($2 == "1"){
				#Hablamos del jugador 4
				if(!$p4_proc){
					if($3 eq "false"){
						if($turno > 1 && $p4_turno == 0 && $3){
							$p4_turno = $turno;
							$p4_puesto = $puesto;
							$puesto--;
						}
					}

					$p4_proc = 1;
				}
			}elsif($2 == "2"){
				#Hablamos del jugador 2
				if(!$p2_proc){
					if($3 eq "false"){
						if($turno > 1 && $p2_turno == 0 && $3){
							$p2_turno = $turno;
							$p2_puesto = $puesto;
							$puesto--;
						}
					}

					$p2_proc = 1;
				}

			}elsif($2 == "3"){
				#Hablamos del jugador 3
				if(!$p3_proc){
					if($3 eq "false"){
						if($turno > 1 && $p3_turno == 0 && $3){
							$p3_turno = $turno;
							$p3_puesto = $puesto;
							$puesto--;
						}
					}

					$p3_proc = 1;
				}

			}elsif($2 == "4"){
				#Hablamos del jugador 1
				if(!$p1_proc){
					if($3 eq "false"){
						if($turno > 1 && $p1_turno == 0 && $3){
							$p1_turno = $turno;
							$p1_puesto = $puesto;
							$puesto--;
						}
					}
					$p1_proc = 1;
				}				
			}
		}
	}
}

if($p1_puesto eq "0"){ $p1_turno = $turno ; $p1_puesto = $puesto; }
if($p2_puesto eq "0"){ $p2_turno = $turno ; $p2_puesto = $puesto; }
if($p3_puesto eq "0"){ $p3_turno = $turno ; $p3_puesto = $puesto; }
if($p4_puesto eq "0"){ $p4_turno = $turno ; $p4_puesto = $puesto; }

print " P1;";
print $p1_turno .";". $p1_puesto;
print "\n";

print " P2;";
print $p2_turno .";". $p2_puesto;
print "\n";

print " P3;";
print $p3_turno .";". $p3_puesto;
print "\n";

print " P4;";
print $p4_turno .";". $p4_puesto;
print "\n";


print LOG "P1\t". $p1_turno . "\n" . $p1_puesto."\r\n";
print LOG "P2\t". $p2_turno . "\n" . $p2_puesto."\r\n";
print LOG "P3\t". $p3_turno . "\n" . $p3_puesto."\r\n";
print LOG "P4\t". $p4_turno . "\n" . $p4_puesto."\r\n";