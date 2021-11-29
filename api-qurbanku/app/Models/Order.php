<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Order extends Model
{
    //
    protected $fillable = [
        'notransaksi','alamattujuan', 'idpenjual', 'idpelanggan', 'total','bayar','kembali','tanggal','flagkirim','waktu'
    ];
}
