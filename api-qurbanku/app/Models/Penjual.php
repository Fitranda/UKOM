<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Penjual extends Model
{
    //
    protected $fillable = [
        'penjual','telp', 'email', 'gambar', 'password','alamat','link','status','ratings'
    ];
}
