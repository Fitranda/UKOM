<?php

namespace App\Http\Controllers;

use App\Models\Favorit;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class FavoritController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index($id)
    {
        //
        $data = DB::table('favorits')
        ->join('pelanggans','favorits.idpelanggan','=','pelanggans.idpelanggan')
        ->join('penjuals','favorits.idpenjual','=','penjuals.idpenjual')
        ->select('favorits.*','pelanggans.pelanggan','penjuals.*')
        ->where('favorits.idpelanggan','=',$id)
        ->where('favorits.like','=',1)
        ->orderBy('penjuals.ratings','desc')
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
            'idpelanggan'=>'required',
            'idpenjual'=>'required',
            'like'=>'required'
        ]);
        $kategori = Favorit::create($request->all());
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
     * @param  \App\Models\Favorit  $favorit
     * @return \Illuminate\Http\Response
     */
    public function show($id,$idp)
    {
        //
        $data = DB::table('favorits')
        ->join('pelanggans','favorits.idpelanggan','=','pelanggans.idpelanggan')
        ->join('penjuals','favorits.idpenjual','=','penjuals.idpenjual')
        ->select('favorits.*','pelanggans.pelanggan','penjuals.*')
        ->where('favorits.idpelanggan','=',$id)
        ->where('favorits.idpenjual','=',$idp)
        ->orderBy('penjuals.ratings','desc')
        ->get();

        return response()->json($data);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Favorit  $favorit
     * @return \Illuminate\Http\Response
     */
    public function edit(Favorit $favorit)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Favorit  $favorit
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
        $this->validate($request, [
            'like' => 'required'
        ]);

        $data = [
            'like' => $request->input('like')
        ];

        $order = Favorit::where('idfavorit', $id)->update($data);
        if ($order) {
            return response()->json([
                'pesan' => 'Pesanan Sudah Dibayar'
            ]);
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Favorit  $favorit
     * @return \Illuminate\Http\Response
     */
    public function destroy(Favorit $favorit)
    {
        //
    }
}
