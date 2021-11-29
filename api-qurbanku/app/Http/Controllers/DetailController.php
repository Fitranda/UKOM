<?php

namespace App\Http\Controllers;

use App\Models\Detail;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class DetailController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
        $data = DB::table('details')
        ->join('orders','details.notransaksi','=','orders.notransaksi')
        ->join('hewans','details.idhewan','=','hewans.idhewan')
        ->join('penjuals','penjuals.idpenjual','=','orders.idpenjual')
        ->join('pelanggans','pelanggans.idpelanggan','=','orders.idpelanggan')
        ->select('details.*','orders.*','penjuals.penjual','pelanggans.pelanggan','hewans.hewan','hewans.harga')
        ->orderBy('details.iddetail','asc')
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
            'notransaksi'=>'required',
            'idhewan'=>'required',
            'hargajual'=>'required',
            'flagada'=>'required'
        ]);
        $kategori = Detail::create($request->all());
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
     * @param  \App\Models\Detail  $detail
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
        $data = DB::table('details')
        ->join('orders','details.notransaksi','=','orders.notransaksi')
        ->join('hewans','details.idhewan','=','hewans.idhewan')
        ->join('penjuals','penjuals.idpenjual','=','orders.idpenjual')
        ->join('pelanggans','pelanggans.idpelanggan','=','orders.idpelanggan')
        ->select('details.*','orders.*','penjuals.penjual','pelanggans.pelanggan','hewans.*')
        ->where('details.notransaksi','=',$id)
        ->orderBy('details.iddetail','asc')
        ->get();

        return response()->json($data);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Detail  $detail
     * @return \Illuminate\Http\Response
     */
    public function edit(Detail $detail)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Detail  $detail
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
        $this->validate($request, [
            'flagada' => 'required'
        ]);

        $data = [
            'flagada' => $request->input('flagada')
        ];

        $order = Detail::where('notransaksi', $id)->update($data);
        if ($order) {
            return response()->json([
                'pesan' => 'Pesanan Sudah Dibayar'
            ]);
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Detail  $detail
     * @return \Illuminate\Http\Response
     */
    public function destroy(Detail $detail)
    {
        //
    }
}
