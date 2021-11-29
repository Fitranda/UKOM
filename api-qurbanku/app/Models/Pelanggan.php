<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Pelanggan extends Model
{
    //
    protected $fillable = [
        'pelanggan','telp', 'email', 'gambar', 'password','alamat','ttl','status'
    ];
}
