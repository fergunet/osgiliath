#!/usr/bin/perl

my $nuevo ="";
my $nuevo_final;

while ($linea=<>){
		chop($linea);
		if($linea=~/^P ([\d]+\.[\d]+) ([\d]+\.[\d]+) ([\d]+) ([\d]+) ([\d]+)/){
			$nuevo.="P ";
			$nuevo.=$1;
			$nuevo.=" ";
			$nuevo.=$2/2;
			$nuevo.=" ";
			$nuevo.=$3;
			$nuevo.=" ";
			$nuevo.=$4;
			$nuevo.=" ";
			$nuevo.=$5;
			$nuevo.="\n";

			$nuevo_final.="P ";
			$nuevo_final.=$1;
			$nuevo_final.=" ";
			$nuevo_final.=($2+24)/2;
			$nuevo_final.=" ";
			if($3 eq "1"){
				$nuevo_final.="3";				
			}elsif($3 eq "2"){
				$nuevo_final.="4"
			}else{
				$nuevo_final.=$3;	
			}
			$nuevo_final.=" ";
			$nuevo_final.=$4;
			$nuevo_final.=" ";
			$nuevo_final.=$5;
			$nuevo_final.="\n";
		}
}

print $nuevo;
print $nuevo_final;

