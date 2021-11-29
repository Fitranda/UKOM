<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Hewan extends Model
{
    //
    protected $fillable = [
        'idkategori','idhewan', 'hewan', 'gambar', 'harga','rincian','flagjual','idpenjual'
    ];
}
