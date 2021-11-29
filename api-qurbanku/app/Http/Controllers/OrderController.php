<?php

namespace App\Http\Controllers;

use App\Models\Order;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class OrderController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
        $data = DB::table('orders')
        ->join('penjuals','penjuals.idpenjual','=','orders.idpenjual')
        ->join('pelanggans','pelanggans.idpelanggan','=','orders.idpelanggan')
        ->select('orders.*','penjuals.penjual','pelanggans.pelanggan','penjuals.gambar')
        ->orderBy('orders.flagkirim','asc')
        ->get();

        return response()->json($data);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create(Request $request)
    {
        //
        $this->validate($request,[
            'notransaksi'=>'required | unique:orders',
            'alamattujuan'=>'required',
            'idpenjual'=>'required',
            'idpelanggan'=>'required',
            'total'=>'required',
            'tanggal'=>'required',
            'waktu'=>'required',
            'flagkirim'=>'required'
        ]);
        $kategori = Order::create($request->all());
        if ($kategori) {
            return response()->json([
                'pesan' => 'Data Sudah Disimpan'
            ]);
        }
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Order  $order
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
        $data = DB::table('orders')
        ->join('penjuals','penjuals.idpenjual','=','orders.idpenjual')
        ->join('pelanggans','pelanggans.idpelanggan','=','orders.idpelanggan')
        ->select('orders.*','penjuals.penjual','pelanggans.pelanggan','penjuals.gambar')
        ->where('notransaksi','=',$id)
        ->orderBy('orders.flagkirim','asc')
        ->get();

        return response()->json($data);
    }

    public function showH($id)
    {
        //
        $data = DB::table('orders')
        ->join('penjuals','penjuals.idpenjual','=','orders.idpenjual')
        ->join('pelanggans','pelanggans.idpelanggan','=','orders.idpelanggan')
        ->select('orders.*','penjuals.penjual','pelanggans.pelanggan','penjuals.gambar')
        ->where('flagkirim','=',2)
        ->where('orders.idpelanggan','=',$id)
        ->orderBy('orders.tanggal','desc')
        ->get();

        return response()->json($data);
    }

    public function showHT($id)
    {
        //
        $data = DB::table('orders')
        ->join('penjuals','penjuals.idpenjual','=','orders.idpenjual')
        ->join('pelanggans','pelanggans.idpelanggan','=','orders.idpelanggan')
        ->select('orders.*','penjuals.penjual','pelanggans.pelanggan','pelanggans.gambar')
        ->where('flagkirim','=',2)
        ->where('orders.idpenjual','=',$id)
        ->orderBy('orders.tanggal','desc')
        ->get();

        return response()->json($data);
    }

    public function showP($id)
    {
        //
        $data = DB::table('orders')
        ->join('penjuals','penjuals.idpenjual','=','orders.idpenjual')
        ->join('pelanggans','pelanggans.idpelanggan','=','orders.idpelanggan')
        ->select('orders.*','penjuals.penjual','pelanggans.pelanggan','penjuals.gambar')
        ->where('flagkirim','<>',2)
        ->where('orders.idpelanggan','=',$id)
        ->orderBy('orders.flagkirim','asc')
        ->get();

        return response()->json($data);
    }

    public function showPT($id)
    {
        //
        $data = DB::table('orders')
        ->join('penjuals','penjuals.idpenjual','=','orders.idpenjual')
        ->join('pelanggans','pelanggans.idpelanggan','=','orders.idpelanggan')
        ->select('orders.*','penjuals.penjual','pelanggans.pelanggan','pelanggans.gambar')
        ->where('flagkirim','<>',2)
        ->where('orders.idpenjual','=',$id)
        ->orderBy('orders.flagkirim','asc')
        ->get();

        return response()->json($data);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Order  $order
     * @return \Illuminate\Http\Response
     */
    public function showtgl($a,$b)
    {
        //
        $data = DB::table('orders')
        ->join('penjuals','penjuals.idpenjual','=','orders.idpenjual')
        ->join('pelanggans','pelanggans.idpelanggan','=','orders.idpelanggan')
        ->select('orders.*','penjuals.penjual','pelanggans.pelanggan','penjuals.gambar')
        ->where('tanggal', '>=', $a)
        ->where('tanggal', '<=', $b)
        ->orderBy('orders.flagkirim','asc')
        ->get();

        return response()->json($data);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Order  $order
     * @return \Illuminate\Http\Response
     */
    public function showall($id,$a,$b)
    {
        //
        $data = DB::table('orders')
        ->join('penjuals','penjuals.idpenjual','=','orders.idpenjual')
        ->join('pelanggans','pelanggans.idpelanggan','=','orders.idpelanggan')
        ->select('orders.*','penjuals.penjual','pelanggans.pelanggan','penjuals.gambar')
        ->where('notransaksi','=',$id)
        ->where('tanggal', '>=', $a)
        ->where('tanggal', '<=', $b)
        ->orderBy('orders.flagkirim','asc')
        ->get();

        return response()->json($data);
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Order  $order
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
        $this->validate($request, [
            'bayar' => 'required | numeric',
            'kembali' => 'required | numeric'
        ]);

        $data = [
            'bayar' => $request->input('bayar'),
            'kembali' => $request->input('kembali')
        ];

        $order = Order::where('notransaksi', $id)->update($data);
        if ($order) {
            return response()->json([
                'pesan' => 'Pesanan Sudah Dibayar'
            ]);
        }
    }

    public function updateflag(Request $request, $id)
    {
        //
        $this->validate($request, [
            'flagkirim' => 'required'
        ]);

        $data = [
            'flagkirim' => $request->input('flagkirim')
        ];

        $order = Order::where('notransaksi', $id)->update($data);
        if ($order) {
            return response()->json([
                'pesan' => 'Pesanan dikirim'
            ]);
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Order  $order
     * @return \Illuminate\Http\Response
     */
    public function destroy(Order $order)
    {
        //
    }
}
