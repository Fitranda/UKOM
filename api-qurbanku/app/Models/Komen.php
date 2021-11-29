<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Komen extends Model
{
    //
    protected $fillable = [
        'idpelanggan', 'idpenjual','rating','komen','tgl'
      ];
}
